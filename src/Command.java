public class Command
{
	public enum Action
	{
		DISPLAY,
		WAIT
	}

	private Action action;
	private Object data;

	public Command(Action action, Object data)
	{
		setAction(action);
		setData(data);
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public Action getAction()
	{
		return action;
	}

	public void setAction(Action action)
	{
		this.action = action;
	}
}
