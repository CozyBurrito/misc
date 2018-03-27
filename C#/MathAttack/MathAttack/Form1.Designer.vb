<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class Form1
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()>
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()>
    Private Sub InitializeComponent()
        Me.Panel1 = New System.Windows.Forms.Panel()
        Me.MenuStrip1 = New System.Windows.Forms.MenuStrip()
        Me.SettingsToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.NumberMinMaxToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.Num1 = New System.Windows.Forms.Label()
        Me.op = New System.Windows.Forms.Label()
        Me.Num2 = New System.Windows.Forms.Label()
        Me.equals = New System.Windows.Forms.Label()
        Me.ansIn = New System.Windows.Forms.TextBox()
        Me.revealAns = New System.Windows.Forms.Button()
        Me.checkBtn = New System.Windows.Forms.Button()
        Me.Panel1.SuspendLayout()
        Me.MenuStrip1.SuspendLayout()
        Me.SuspendLayout()
        '
        'Panel1
        '
        Me.Panel1.BackgroundImageLayout = System.Windows.Forms.ImageLayout.None
        Me.Panel1.Controls.Add(Me.Num1)
        Me.Panel1.Controls.Add(Me.op)
        Me.Panel1.Controls.Add(Me.Num2)
        Me.Panel1.Controls.Add(Me.equals)
        Me.Panel1.Controls.Add(Me.ansIn)
        Me.Panel1.Controls.Add(Me.revealAns)
        Me.Panel1.Controls.Add(Me.checkBtn)
        Me.Panel1.Controls.Add(Me.MenuStrip1)
        Me.Panel1.Dock = System.Windows.Forms.DockStyle.Fill
        Me.Panel1.Location = New System.Drawing.Point(0, 0)
        Me.Panel1.Name = "Panel1"
        Me.Panel1.Size = New System.Drawing.Size(712, 241)
        Me.Panel1.TabIndex = 0
        '
        'MenuStrip1
        '
        Me.MenuStrip1.BackColor = System.Drawing.SystemColors.ControlLight
        Me.MenuStrip1.Items.AddRange(New System.Windows.Forms.ToolStripItem() {Me.SettingsToolStripMenuItem})
        Me.MenuStrip1.Location = New System.Drawing.Point(0, 0)
        Me.MenuStrip1.Name = "MenuStrip1"
        Me.MenuStrip1.Size = New System.Drawing.Size(712, 24)
        Me.MenuStrip1.TabIndex = 12
        Me.MenuStrip1.Text = "MenuStrip1"
        '
        'SettingsToolStripMenuItem
        '
        Me.SettingsToolStripMenuItem.DropDownItems.AddRange(New System.Windows.Forms.ToolStripItem() {Me.NumberMinMaxToolStripMenuItem})
        Me.SettingsToolStripMenuItem.Name = "SettingsToolStripMenuItem"
        Me.SettingsToolStripMenuItem.Size = New System.Drawing.Size(61, 20)
        Me.SettingsToolStripMenuItem.Text = "Settings"
        '
        'NumberMinMaxToolStripMenuItem
        '
        Me.NumberMinMaxToolStripMenuItem.Name = "NumberMinMaxToolStripMenuItem"
        Me.NumberMinMaxToolStripMenuItem.Size = New System.Drawing.Size(169, 22)
        Me.NumberMinMaxToolStripMenuItem.Text = "Number Min/Max"
        '
        'Num1
        '
        Me.Num1.AutoSize = True
        Me.Num1.Font = New System.Drawing.Font("Microsoft Sans Serif", 30.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Num1.Location = New System.Drawing.Point(32, 54)
        Me.Num1.Name = "Num1"
        Me.Num1.Size = New System.Drawing.Size(126, 46)
        Me.Num1.TabIndex = 37
        Me.Num1.Text = "Num1"
        '
        'op
        '
        Me.op.AutoSize = True
        Me.op.Font = New System.Drawing.Font("Microsoft Sans Serif", 30.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.op.Location = New System.Drawing.Point(173, 54)
        Me.op.Name = "op"
        Me.op.Size = New System.Drawing.Size(73, 46)
        Me.op.TabIndex = 38
        Me.op.Text = "Op"
        '
        'Num2
        '
        Me.Num2.AutoSize = True
        Me.Num2.Font = New System.Drawing.Font("Microsoft Sans Serif", 30.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Num2.Location = New System.Drawing.Point(261, 54)
        Me.Num2.Name = "Num2"
        Me.Num2.Size = New System.Drawing.Size(126, 46)
        Me.Num2.TabIndex = 39
        Me.Num2.Text = "Num2"
        '
        'equals
        '
        Me.equals.AutoSize = True
        Me.equals.Font = New System.Drawing.Font("Microsoft Sans Serif", 30.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.equals.Location = New System.Drawing.Point(393, 54)
        Me.equals.Name = "equals"
        Me.equals.Size = New System.Drawing.Size(43, 46)
        Me.equals.TabIndex = 40
        Me.equals.Text = "="
        '
        'ansIn
        '
        Me.ansIn.Font = New System.Drawing.Font("Microsoft Sans Serif", 30.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ansIn.Location = New System.Drawing.Point(451, 51)
        Me.ansIn.Name = "ansIn"
        Me.ansIn.Size = New System.Drawing.Size(226, 53)
        Me.ansIn.TabIndex = 41
        '
        'revealAns
        '
        Me.revealAns.Font = New System.Drawing.Font("Microsoft Sans Serif", 15.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.revealAns.Location = New System.Drawing.Point(98, 140)
        Me.revealAns.Name = "revealAns"
        Me.revealAns.Size = New System.Drawing.Size(225, 77)
        Me.revealAns.TabIndex = 43
        Me.revealAns.Text = "Answer"
        Me.revealAns.UseVisualStyleBackColor = True
        '
        'checkBtn
        '
        Me.checkBtn.Font = New System.Drawing.Font("Microsoft Sans Serif", 15.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.checkBtn.Location = New System.Drawing.Point(369, 140)
        Me.checkBtn.Name = "checkBtn"
        Me.checkBtn.Size = New System.Drawing.Size(225, 77)
        Me.checkBtn.TabIndex = 42
        Me.checkBtn.Text = "Check"
        Me.checkBtn.UseVisualStyleBackColor = True
        '
        'Form1
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(712, 241)
        Me.Controls.Add(Me.Panel1)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.MainMenuStrip = Me.MenuStrip1
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "Form1"
        Me.SizeGripStyle = System.Windows.Forms.SizeGripStyle.Hide
        Me.Text = "Math Attack"
        Me.Panel1.ResumeLayout(False)
        Me.Panel1.PerformLayout()
        Me.MenuStrip1.ResumeLayout(False)
        Me.MenuStrip1.PerformLayout()
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents MenuStrip1 As MenuStrip
    Friend WithEvents SettingsToolStripMenuItem As ToolStripMenuItem
    Friend WithEvents NumberMinMaxToolStripMenuItem As ToolStripMenuItem
    Friend WithEvents Panel1 As Panel
    Friend WithEvents Num1 As Label
    Friend WithEvents op As Label
    Friend WithEvents Num2 As Label
    Friend WithEvents equals As Label
    Friend WithEvents ansIn As TextBox
    Friend WithEvents revealAns As Button
    Friend WithEvents checkBtn As Button
End Class
