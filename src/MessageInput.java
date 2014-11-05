import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MessageInput extends JPanel
{
	public static final String ENABLED   = "enabled";
	public static final String TEXT      = "text";
	public static final String FREQUENCY = "frequency";
	public static final String DURATION  = "duration";

	private JCheckBox  enabled;
	private JTextField message;
	private JSpinner   frequencySpinner;
	private JSpinner   durationSpinner;

	public MessageInput()
	{
		setLayout(new MigLayout("wrap 10, gapx 7"));

		SpinnerNumberModel frequencyModel = new SpinnerNumberModel(5, 3, 35, 1);
		SpinnerNumberModel durationModel = new SpinnerNumberModel(5, 3, 35, 1);

		enabled = new JCheckBox();
		message = new JTextField("Your text here");
		frequencySpinner = new JSpinner(frequencyModel);
		durationSpinner = new JSpinner(durationModel);

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

	public boolean isEnabled()
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

	public void enable()
	{
		setEnabled(true);
	}

	public void disable()
	{
		setEnabled(false);
	}

	public void setEnabled(boolean value)
	{
		enabled.setEnabled(value);
		message.setEnabled(value);
		frequencySpinner.setEnabled(value);
		durationSpinner.setEnabled(value);
	}

	public String toString()
	{
		String string = "{";
		string += "\"" + ENABLED + "\": " + isEnabled() + ", ";
		string += "\"" + TEXT + "\": \"" + getText() + "\", ";
		string += "\"" + FREQUENCY + "\": " + getFrequency() + ", ";
		string += "\"" + DURATION + "\": " + getDuration() + " ";
		string += "}";
		return string;
	}

	public void fromString(String json)
	{
		enabled.setEnabled(Boolean.valueOf(Utils.extractValue(json, ENABLED)));
		message.setText(Utils.extractValue(json, TEXT));
		frequencySpinner.setValue(
				Integer.valueOf(Utils.extractValue(json, FREQUENCY)));
		durationSpinner.setValue(
				Integer.valueOf(Utils.extractValue(json, DURATION)));
	}

	public void fromMessage(Message msg)
	{
		enabled.setEnabled(true);
		message.setText(msg.getText());
		frequencySpinner.setValue(msg.getTime());
		durationSpinner.setValue(msg.getDuration());
	}
}
