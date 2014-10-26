public class Message
{
	private String text;
	private int    time;
	private int    duration;

	public Message()
	{
		this("");
	}

	public Message(String text)
	{
		this(text, -1);
	}

	public Message(String text, int time)
	{
		this(text, time, -1);
	}

	public Message(String text, int time, int duration)
	{
		setText(text);
		setTime(time);
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

	public int getTime()
	{
		return time;
	}

	public void setTime(int time)
	{
		this.time = time;
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
		string += "\"time\": " + getTime() + ", ";
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
					getTime() == message.getTime() &&
					getDuration() == message.getDuration();
		}
		return false;
	}

	@Override
	public Message clone()
	{
		return new Message(getText(), getTime(), getDuration());
	}

	public void update(Message message)
	{
		setText(message.getText());
		setTime(message.getTime());
		setDuration(message.getDuration());
	}
}
