using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace A3
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {

        bool isMoving = false;
        Vector mousePos = new Vector();
        int imgZIndexCounter = 0;
        Image sourceImg = null;

        public MainWindow()
        {
            InitializeComponent();
        }

        private void canvas_Drop(object sender, DragEventArgs e)
        {
            string[] files = (string[]) e.Data.GetData(DataFormats.FileDrop);

            foreach (string filePath in files)
            {

                BitmapImage bmp = new BitmapImage();
                bmp.BeginInit();
                bmp.UriSource = new Uri(filePath);
                bmp.EndInit();

                Image img = new Image();
                img.Source = bmp;
                img.Width = 100;
                img.Height = 100;
                img.Stretch = Stretch.Fill;
                img.SetValue(Canvas.LeftProperty, e.GetPosition(canvas).X);
                img.SetValue(Canvas.TopProperty, e.GetPosition(canvas).Y);

                this.canvas.Children.Add(img);
                
            }
             
        }

        private void canvas_MouseDown(object sender, MouseButtonEventArgs e)
        {
            isMoving = true;

            sourceImg = e.Source as Image;

            Canvas.SetZIndex(sourceImg, imgZIndexCounter++);
            Canvas.SetZIndex(button, imgZIndexCounter++);

            mousePos.X = e.GetPosition(canvas).X;
            mousePos.Y = e.GetPosition(canvas).Y;

        }

        private void canvas_MouseUp(object sender, MouseButtonEventArgs e)
        {
            isMoving = false;
            sourceImg = null;
        }

        private void canvas_MouseMove(object sender, MouseEventArgs e)
        {

            if (isMoving)
            {
                Vector startPos = mousePos;

                mousePos.X = e.GetPosition(canvas).X;
                mousePos.Y = e.GetPosition(canvas).Y;

                Vector endPos = mousePos;

                Canvas.SetLeft(sourceImg, Canvas.GetLeft(sourceImg) + (endPos.X - startPos.X));
                Canvas.SetTop(sourceImg, Canvas.GetTop(sourceImg) + (endPos.Y - startPos.Y));
            }
        }

        private void canvas_MouseLeave(object sender, MouseEventArgs e)
        {
            isMoving = false;
            sourceImg = null;
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {

            DoubleAnimation sortX = new DoubleAnimation();
            DoubleAnimation sortY = new DoubleAnimation();

            for (int i = 1, x = 0, y = 40; i < this.canvas.Children.Count; i++)
            {

                sortX.From = Canvas.GetLeft(this.canvas.Children[i]);
                sortY.From = Canvas.GetTop(this.canvas.Children[i]);

                sortX.To = x;
                sortY.To = y;

                sortX.Duration = new Duration(TimeSpan.FromSeconds(1));
                sortY.Duration = new Duration(TimeSpan.FromSeconds(1));

                sortX.Completed += new EventHandler(sortX_Completed);
                sortY.Completed += new EventHandler(sortY_Completed);

                this.canvas.Children[i].BeginAnimation(Canvas.LeftProperty, sortX);
                this.canvas.Children[i].BeginAnimation(Canvas.TopProperty, sortY);

                if (x + 100 > canvas.ActualWidth - 100)
                {
                    y += 100;
                    x = 0;
                }
                else
                {
                    x += 100;
                }

            }

        }

        private void sortX_Completed(object sender, EventArgs e)
        {
            for (int i = 1; i < this.canvas.Children.Count; i++)
            {
                Canvas.SetLeft(this.canvas.Children[i], Canvas.GetLeft(this.canvas.Children[i]));
                this.canvas.Children[i].BeginAnimation(Canvas.LeftProperty, null);
            }
        }

        private void sortY_Completed(object sender, EventArgs e)
        {
            for (int i = 1; i < this.canvas.Children.Count; i++)
            {
                Canvas.SetTop(this.canvas.Children[i], Canvas.GetTop(this.canvas.Children[i]));
                this.canvas.Children[i].BeginAnimation(Canvas.TopProperty, null);
            }
            
        }
    }
}
