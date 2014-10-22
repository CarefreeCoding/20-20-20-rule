public class Message
{
	private String text;
	private int    frequency;
	private int    duration;

	public Message()
	{
		this("");
	}

	public Message(String text)
	{
		this(text, -1);
	}

	public Message(String text, int frequency)
	{
		this(text, frequency, -1);
	}

	public Message(String text, int frequency, int duration)
	{
		setText(text);
		setFrequency(frequency);
		setDuration(duration);
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public int getFrequency()
	{
		return frequency;
	}

	public void setFrequency(int frequency)
	{
		this.frequency = frequency;
	}

	public int getDuration()
	{
		return duration;
	}

	public void setDuration(int duration)
	{
		this.duration = duration;
	}

	@Override
	public String toString()
	{
		String string = "{";
		string += "\"text\": \"" + getText() + "\", ";
		string += "\"frequency\": " + getFrequency() + ", ";
		string += "\"duration\": " + getDuration() + " ";
		string += "}";
		return string;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Message)
		{
			Message message = (Message) obj;
			return getText().equalsIgnoreCase(message.getText()) &&
					getFrequency() == message.getFrequency() &&
					getDuration() == message.getDuration();
		}
		return false;
	}

	@Override
	public Message clone()
	{
		return new Message(getText(), getFrequency(), getDuration());
	}

	public void update(Message message)
	{
		setText(message.getText());
		setFrequency(message.getFrequency());
		setDuration(message.getDuration());
	}
}
