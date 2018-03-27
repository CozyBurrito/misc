using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Timers;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace A2
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {

        // Stopwatch Variables
        private bool stopwatchStartIsClicked = false;
        private Timer stopwatchTimer = new Timer(1000);
        private int stopwatchHrs = 0, stopwatchMins = 0, stopwatchSecs = 0;

        // Timer Variables
        private Timer timerOneSec = new Timer(1000);
        private Timer timerMain;
        private TimeSpan timerTimeSpan;
        private bool timerIsRunning = false;

        // Clock Variables
        private Timer clockTimer = new Timer(1000);

        public MainWindow()
        {
            InitializeComponent();

            stopwatchTimer.Elapsed += OnTimedEventStopwatch;
            stopwatchTimer.AutoReset = true;
            stopwatchTimer.Enabled = false;

            timerOneSec.Elapsed += OnTimedEventTimerOneSec;
            timerOneSec.AutoReset = true;

            clockTimer.Elapsed += OnTimedEventClock;
            clockTimer.AutoReset = true;
            clockTimer.Start();

        }

        // Stopwatch functions

        private void buttonStartStopStopwatch_Click(object sender, RoutedEventArgs e)
        {
            Button btn = (Button)sender;

            if (stopwatchStartIsClicked)
            {
                stopwatchTimer.Stop();

                btn.Content = "Start";
                btn.Foreground = Brushes.DarkGreen;
                btn.BorderBrush = Brushes.Green;

                buttonRecResetStopwatch.Content = "Reset";
                buttonRecResetStopwatch.Foreground = Brushes.DarkBlue;
                buttonRecResetStopwatch.BorderBrush = Brushes.Blue;

                stopwatchStartIsClicked = !stopwatchStartIsClicked;
            }
            else
            {
                stopwatchTimer.Start();

                btn.Content = "Stop";
                btn.Foreground = Brushes.DarkRed;
                btn.BorderBrush = Brushes.Red;

                buttonRecResetStopwatch.Content = "Rec";
                buttonRecResetStopwatch.Foreground = Brushes.DarkOrange;
                buttonRecResetStopwatch.BorderBrush = Brushes.Orange;

                stopwatchStartIsClicked = !stopwatchStartIsClicked;
            }
        }

        private void buttonRecResetStopwatch_Click(object sender, RoutedEventArgs e)
        {
            Button btn = (Button)sender;

            if (stopwatchStartIsClicked)
            {
                listView.Items.Add(labelStopwatch.Content);
            }
            else
            {
                stopwatchHrs = 0;
                stopwatchMins = 0;
                stopwatchSecs = 0;
                labelStopwatch.Content = new TimeSpan(stopwatchHrs, stopwatchMins, stopwatchSecs);

                listView.Items.Clear();
            }

        }

        private void OnTimedEventStopwatch(object sender, ElapsedEventArgs e)
        {
            Dispatcher.Invoke(new Action(() =>
            {
                stopwatchSecs++;

                if (stopwatchSecs == 60)
                {
                    stopwatchSecs = 0;
                    stopwatchMins += 1;
                }

                if (stopwatchMins == 60)
                {
                    stopwatchSecs = 0;
                    stopwatchMins = 0;
                    stopwatchHrs += 1;
                }

                labelStopwatch.Content = new TimeSpan(stopwatchHrs, stopwatchMins, stopwatchSecs);

            }));
        }


        // Timer functions

        private void buttonTimerReset_Click(object sender, RoutedEventArgs e)
        {
            timerOneSec.Stop();
            timerMain.Stop();

            timerIsRunning = false;

            timerTimeSpan = TimeSpan.FromMilliseconds(0);

            labelTimer.Foreground = Brushes.Black;

            labelTimer.Content = timerTimeSpan;
        }

        private void buttonTimerStart_Click(object sender, RoutedEventArgs e)
        {
            if (!timerIsRunning)
            {
                int hrs = 0, min = 0, sec = 0;

                Int32.TryParse(textBoxH.Text, out hrs);
                Int32.TryParse(textBoxM.Text, out min);
                Int32.TryParse(textBoxS.Text, out sec);

                if (hrs == 6 && min == 6 && sec == 6)
                {
                    System.Diagnostics.Process.Start("http://i.imgur.com/zQYuXJC.gifv");
                }


                if (hrs > 0 || min > 0 || sec > 0)
                {
                    ulong ms = (ulong)((hrs * 3600000) + (min * 60000) + (sec * 1000));

                    timerTimeSpan = TimeSpan.FromMilliseconds(ms);
                    labelTimer.Content = timerTimeSpan;

                    timerMain = new Timer(ms);
                    timerMain.Elapsed += OnTimedEventTimerMain;

                    timerOneSec.Start();
                    timerMain.Start();

                    timerIsRunning = true;
                }
            }
        }

        private void OnTimedEventTimerMain(object sender, ElapsedEventArgs e)
        {
            timerOneSec.Stop();
            timerMain.Stop();

            timerIsRunning = false;

            Dispatcher.Invoke(new Action(() =>
            {
                if (timerTimeSpan.TotalSeconds > 0)
                {
                    timerTimeSpan = timerTimeSpan.Subtract(TimeSpan.FromSeconds(1));
                }

                labelTimer.Foreground = Brushes.Black;

                labelTimer.Content = timerTimeSpan;

            }));

        }

        private void OnTimedEventTimerOneSec(object sender, ElapsedEventArgs e)
        {
            Dispatcher.Invoke(new Action(() =>
            {
                timerTimeSpan = timerTimeSpan.Subtract(TimeSpan.FromSeconds(1));

                if (timerTimeSpan.TotalSeconds >= 1 && timerTimeSpan.TotalSeconds <= 3)
                {
                    labelTimer.Foreground = Brushes.Red;
                }

                labelTimer.Content = timerTimeSpan;

            }));

        }


        // Clock functions

        private void OnTimedEventClock(object sender, ElapsedEventArgs e)
        {
            Dispatcher.Invoke(new Action(() =>
            {
                labelClock.Content = DateTime.Now.ToString();
            }));
        }


    }
}
