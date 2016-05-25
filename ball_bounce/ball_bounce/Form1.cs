using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace ball_bounce
{
    public partial class Form1 : Form
    {
        Timer tm;
        Point p;
        int xVal, yVal;
        public Form1()
        {
            InitializeComponent();
            tm = new Timer();
            tm.Interval = 10;
            p = new Point();
            p.X = 300;
            p.Y = 300;
            xVal = 10;
            yVal = 10;
            ballPB.Location = p;
            tm.Enabled = true;
            tm.Tick += new EventHandler(move);
        }

        private void move(object sender, EventArgs e)
        {
            if (p.X >= pictureBox1.Width - 40 || p.X <= 0)
                xVal = -xVal;
            if (p.Y >= pictureBox1.Height - 40 || p.Y <= 0)
                yVal = -yVal;
            p.X += xVal;
            p.Y += yVal;
            ballPB.Location = p;
        }
    }
}
