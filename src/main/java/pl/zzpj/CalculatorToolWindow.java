package pl.zzpj;

import javax.swing.*;
import java.awt.event.ActionListener;

import static pl.zzpj.Calculator.Operation;

public class CalculatorToolWindow {
    private JPanel grid;
    private JButton a0Button;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton plusMinusButton;
    private JButton dotButton;
    private JTextField resultTextField;
    private JButton backspaceButton;
    private JButton subButton;
    private JButton addButton;
    private JButton equalsButton;
    private JButton cButton;
    private JButton divButton;
    private JButton mulButton;
    private JButton percentButton;
    private JList<Float> historyListView;
    private JButton clearHistoryButton;
    private DefaultListModel<Float> historyListModel;

    private String register;

    private Calculator calculator;

    public CalculatorToolWindow() {
        calculator = new Calculator();
        register = "0";
        initComponents();
    }

    private void initComponents() {
        resultTextField.setText(register);
        historyListModel = new DefaultListModel<>();
        historyListModel.addAll(calculator.getHistory());
        historyListView.setModel(historyListModel);

        ActionListener numButtonsListener = e -> {
            register += ((JButton) e.getSource()).getText();
            resultTextField.setText(register);
        };

        a0Button.addActionListener(numButtonsListener);
        a1Button.addActionListener(numButtonsListener);
        a2Button.addActionListener(numButtonsListener);
        a3Button.addActionListener(numButtonsListener);
        a4Button.addActionListener(numButtonsListener);
        a5Button.addActionListener(numButtonsListener);
        a6Button.addActionListener(numButtonsListener);
        a7Button.addActionListener(numButtonsListener);
        a8Button.addActionListener(numButtonsListener);
        a9Button.addActionListener(numButtonsListener);

        ActionListener opButtonsListener = e -> {
            JButton sourceButton = (JButton) e.getSource();
            calculator.setRegister(register.length() > 0 ? Float.parseFloat(register) : calculator.getRegister());

            if (sourceButton == addButton)
                calculator.setOperation(Operation.OP_ADD);
            else if (sourceButton == subButton)
                calculator.setOperation(Operation.OP_SUB);
            else if (sourceButton == mulButton)
                calculator.setOperation(Operation.OP_MUL);
            else if (sourceButton == divButton)
                calculator.setOperation(Operation.OP_DIV);
            else if (sourceButton == equalsButton) {
                calculator.setOperation(Operation.OP_EQU);
                updateListView();
            }

            register = "";
            resultTextField.setText(calculator.getAccumulator().toString());
        };
        subButton.addActionListener(opButtonsListener);
        addButton.addActionListener(opButtonsListener);
        equalsButton.addActionListener(opButtonsListener);
        divButton.addActionListener(opButtonsListener);
        mulButton.addActionListener(opButtonsListener);

        backspaceButton.addActionListener(e -> {
            if (register.length() > 1) {
                register = register.substring(0, register.length() - 1);
                resultTextField.setText(register);
            } else {
                register = "";
                resultTextField.setText("0");
            }
        });

        cButton.addActionListener(e -> {
            calculator.reset();
            register = "";
            resultTextField.setText("0");
        });

        clearHistoryButton.addActionListener(e -> {
            calculator.clearHistory();
            updateListView();
        });
    }

    private void updateListView() {
        historyListModel.clear();
        historyListModel.addAll(calculator.getHistory());
    }

    public JPanel getContent() {
        return grid;
    }
}
