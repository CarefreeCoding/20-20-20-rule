import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MessageInput extends JPanel
{
	private JCheckBox  enabled;
	private JTextField message;
	private JSpinner   frequencySpinner;
	private JSpinner   durationSpinner;

	public MessageInput()
	{
		setLayout(new MigLayout("wrap 10, gapx 7"));

		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(5, 3, 35, 1);

		enabled = new JCheckBox();
		message = new JTextField("Your text here");
		frequencySpinner = new JSpinner(spinnerModel);
		durationSpinner = new JSpinner(spinnerModel);

		add(enabled);
		add(new JLabel("Show text "));
		add(message);
		add(new JLabel(" every "));
		add(frequencySpinner);
		add(new JLabel(" minutes for "));
		add(durationSpinner);
		add(new JLabel(" seconds "));

		enabled.setSelected(true);
	}

	public boolean isOn()
	{
		return enabled.isSelected();
	}

	public String getText()
	{
		return message.getText();
	}

	public int getFrequency()
	{
		return Integer.valueOf(String.valueOf(frequencySpinner.getValue()));
	}

	public int getDuration()
	{
		return Integer.valueOf(String.valueOf(durationSpinner.getValue()));
	}

	public Message getMessage()
	{
		return new Message(getText(), getFrequency(), getDuration());
	}
}
