package pl.zzpj;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static pl.zzpj.Calculator.Operation;

public class CalculatorToolWindow {
    private JPanel grid;
    private JButton a7Button;
    private JButton a4Button;
    private JButton a8Button;
    private JButton a1Button;
    private JButton a9Button;
    private JButton a6Button;
    private JButton a3Button;
    private JButton a5Button;
    private JButton a2Button;
    private JButton plusMinusButton;
    private JButton a0Button;
    private JButton dotButton;
    private JTextField textField;
    private JButton backspaceButton;
    private JButton subButton;
    private JButton addButton;
    private JButton equalsButton;
    private JButton cButton;
    private JButton divButton;
    private JButton mulButton;
    private JButton percentButton;

    private String register;

    private Calculator calculator;

    public CalculatorToolWindow() {
        calculator = new Calculator();
        register = "0";
        initComponents();
    }

    private void initComponents() {
        textField.setText(register);

        ActionListener numButtonsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register += ((JButton) e.getSource()).getText();
                textField.setText(register);
            }
        };

        a7Button.addActionListener(numButtonsListener);
        a4Button.addActionListener(numButtonsListener);
        a8Button.addActionListener(numButtonsListener);
        a9Button.addActionListener(numButtonsListener);
        a6Button.addActionListener(numButtonsListener);
        a3Button.addActionListener(numButtonsListener);
        a1Button.addActionListener(numButtonsListener);
        a5Button.addActionListener(numButtonsListener);
        a2Button.addActionListener(numButtonsListener);
        a0Button.addActionListener(numButtonsListener);

        ActionListener opButtonsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton sourceButton = (JButton) e.getSource();
                if (register.length() > 0)
                    calculator.setRegister(Long.parseLong(register));
                else
                    calculator.setRegister(0L);

                if (sourceButton == addButton)
                    calculator.setOperation(Operation.OP_ADD);
                else if (sourceButton == subButton)
                    calculator.setOperation(Operation.OP_SUB);
                else if (sourceButton == mulButton)
                    calculator.setOperation(Operation.OP_MUL);
                else if (sourceButton == divButton)
                    calculator.setOperation(Operation.OP_DIV);
                else if (sourceButton == equalsButton)
                    calculator.setOperation(Operation.OP_EQU);

                register = "";
                textField.setText(calculator.getAccumulator().toString());
            }
        };
        subButton.addActionListener(opButtonsListener);
        addButton.addActionListener(opButtonsListener);
        equalsButton.addActionListener(opButtonsListener);
        divButton.addActionListener(opButtonsListener);
        mulButton.addActionListener(opButtonsListener);

        backspaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (register.length() > 1) {
                    register = register.substring(0, register.length() - 1);
                    textField.setText(register);
                }
                else {
                    register = "";
                    textField.setText("0");
                }
            }
        });

        cButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.reset();
                register = "";
                textField.setText("0");
            }
        });
    }

    public JPanel getContent() {
        return grid;
    }
}
