Public Class Form1
    Dim number1 As Double
    Dim number2 As Double
    Dim selOp As String
    Dim ans As Double
    Dim ops = New String() {"+", "-", "/", "x"}

    Dim num1Min As Double = 99999
    Dim num1Max As Double = 99999
    Dim num2Min As Double = 99999
    Dim num2Max As Double = 99999

    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        genEquation()
    End Sub

    Private Sub revealAns_Click(sender As Object, e As EventArgs)
        ansIn.Text = ans
    End Sub

    Private Sub ansIn_KeyDown(sender As Object, e As KeyEventArgs)
        If e.KeyCode = Keys.Enter Then
            check()
        End If
    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs)
        check()
    End Sub

    Private Sub check()
        If ansIn.Text.Trim = ans.ToString Then
            genEquation()
        End If
    End Sub

    Private Sub genEquation()
        Dim rnd As New Random()

        number1 = rnd.Next(num1Min, num1Max + 1)
        number2 = rnd.Next(num2Min, num2Max + 1)
        selOp = ops(rnd.Next(0, 4))

        Num1.Text = number1
        Num2.Text = number2
        op.Text = selOp
        ansIn.Text = ""

        If selOp = "+" Then
            ans = number1 + number2
        ElseIf selOp = "-" Then
            ans = number1 - number2
        ElseIf selOp = "x" Then
            ans = number1 * number2
        ElseIf selOp = "/" Then
            If number2 = 0 Then
                ans = ""
            Else
                ans = number1 / number2
                ans = Decimal.Round(ans, 2, MidpointRounding.AwayFromZero)
            End If
        End If
    End Sub

End Class
