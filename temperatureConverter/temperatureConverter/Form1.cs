using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace temperatureConverter
{
    public partial class Form1 : Form
    {
        double inp, r;
        public Form1()
        {
            InitializeComponent();
            inp = 0;
            r = 0;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            inp = Convert.ToDouble(textBox1.Text);
            if (comboBox1.SelectedIndex == 1 && comboBox2.SelectedIndex == 2)
            {
                r = (inp / 5) * 9 + 32;
            }
            else if (comboBox1.SelectedIndex == 2 && comboBox2.SelectedIndex == 1)
            {
                r = (inp - 32) * 5 / 9;
            }
            else
            {
                r = inp;
            }
            label5.Text = r.ToString();
        }
    }
}
