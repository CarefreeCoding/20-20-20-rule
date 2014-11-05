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
					message.setFrequency(i);
					messages.add(message);
				}
			}
		}
		return messages;
	}

	public static String extractValue(String json, String variable)
	{
		int index = json.indexOf(variable);
		if (index == -1)
		{
			return "";
		}
		int start = json.indexOf(":", index);
		if (start == -1)
		{
			return "";
		}
		start++;
		int end = json.indexOf(",", start);
		int veryEnd = json.indexOf("}");
		if (end == -1 || veryEnd < end)
		{
			end = veryEnd;
		}
		String response = json.substring(start, end);
		response = response.trim();
		if (response.charAt(0) == '\"')
		{
			response = response.substring(1, response.length() - 1);
		}

		return response;
	}
}
