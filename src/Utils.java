import java.util.LinkedList;
import java.util.List;

public class Utils
{
	public static LinkedList<Message> organizeMessages(
			List<MessageInput> messageInputs)
	{
		// find smallest and largest frequency in the messages
		int max = 0;
		int min = 9999;
		int frequency;
		for (MessageInput input : messageInputs)
		{
			if (!input.isEnabled())
			{
				continue;
			}
			frequency = input.getFrequency();
			if (max < frequency)
			{
				max = frequency;
			}
			if (min > frequency)
			{
				min = frequency;
			}
		}

		// find common divisible number
		int maxLoop;
		boolean found;
		for (int i = 1; ; i++)
		{
			maxLoop = max * i;
			found = true;
			for (MessageInput input : messageInputs)
			{
				if (!input.isEnabled())
				{
					continue;
				}
				if (maxLoop % input.getFrequency() != 0)
				{
					found = false;
					break;
				}
			}
			if (found)
			{
				break;
			}
		}

		// find common step size
		int stepSize = 1;
		for (int i = max; i > 1; i--)
		{
			found = true;
			for (MessageInput input : messageInputs)
			{
				if (!input.isEnabled())
				{
					continue;
				}
				if (input.getFrequency() % i != 0)
				{
					found = false;
					break;
				}
			}
			if (found)
			{
				stepSize = i;
			}
		}

		LinkedList<Message> messages = new LinkedList<Message>();
		for (int i = stepSize; i <= maxLoop; i += stepSize)
		{
			for (MessageInput input : messageInputs)
			{
				if (!input.isEnabled())
				{
					continue;
				}
				if (i % input.getFrequency() == 0)
				{
					Message message = input.getMessage();
					message.setTime(i);
					messages.add(message);
				}
			}
		}
		return messages;
	}
}
