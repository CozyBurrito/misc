using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace A1
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {

        private bool isClicked = false;
        private SolidColorBrush colorA = new SolidColorBrush((Color) ColorConverter.ConvertFromString("#FF4CB5F5"));
        private SolidColorBrush colorB = new SolidColorBrush((Color) ColorConverter.ConvertFromString("#FF1E656D"));

        public MainWindow()
        {
            InitializeComponent();
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            if(isClicked)
            {
                label.Foreground = colorA;
                button.Foreground = colorB;
                button.BorderBrush = Brushes.Aqua;
                isClicked = !isClicked;
            }
            else
            {
                label.Foreground = colorB;
                button.Foreground = colorA;
                button.BorderBrush = Brushes.DarkCyan;
                isClicked = !isClicked;
            }
        }

        private void slider_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
        {
            label.Opacity = slider.Value / 100;
        }

        private void textBox_TextChanged(object sender, TextChangedEventArgs e)
        {
            String text = textBox.Text;
            label.Content = text;

            if(text.Equals("flavortownusa", StringComparison.OrdinalIgnoreCase))
            {
                System.Diagnostics.Process.Start("http://i.imgur.com/VsQArrU.gifv");
            }

        }
    }
}
