import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class TimerControl
{
	public static final int SECOND = 1000;
	public static final int MINUTE = SECOND + 60;

	private InvisibleWindow message;

	private LinkedList<Message> queue;

	private Timer          counter;
	private int            time;
	private ActionListener event;

	public TimerControl()
	{
		message = new InvisibleWindow();
		event = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				stop();
				if (shouldWeDisplay())
				{
					displayMessage();
				}
				else
				{
					waitForNextMessage();
				}
			}
		};
	}

	public void start(List<MessageInput> messageInputs)
	{
		queue = Utils.organizeMessages(messageInputs);
		time = 0;
		event.actionPerformed(null);
	}

	public void stop()
	{
		if (counter != null)
		{
			counter.stop();
		}
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				if (message != null)
				{
					message.setVisible(false);
				}
			}
		});
	}

	private void displayMessage()
	{
		// get the message we will display
		Message msg = queue.poll();
		int timeToSleep = msg.getDuration();
		// prepare
		counter = new Timer(timeToSleep * SECOND, event);
		// set text and display it
		message.setText(msg.getText());
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				message.setVisible(true);
			}
		});
		// add it to the end of the queue
		queue.push(msg);
		// start waiting
		counter.start();
	}

	private void waitForNextMessage()
	{
		// see how long we have to wait
		int timeToSleep = timeToSleep();
		// prepare
		counter = new Timer(timeToSleep * MINUTE, event);
		// start waiting
		counter.start();
	}

	private int timeToSleep()
	{
		int frequency = queue.peek().getFrequency();
		return frequency - (time % frequency);
	}

	private boolean shouldWeDisplay()
	{
		return timeToSleep() == 0;
	}
}
