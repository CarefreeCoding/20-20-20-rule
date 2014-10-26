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
	private ActionListener event;
	private int            time;

	public TimerControl()
	{
		message = new InvisibleWindow();
		event = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				eventAction();
			}
		};
		counter = new Timer(0, event);
	}

	public void start(List<MessageInput> messageInputs)
	{
		queue = Utils.organizeMessages(messageInputs);
		time = 0;
		eventAction();
	}

	public void stop()
	{
		counter.stop();
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				message.setVisible(false);
			}
		});
	}

	private void eventAction()
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

	private void displayMessage()
	{
		// get the message we will display
		Message msg = queue.poll();
		int timeToSleep = msg.getDuration();
		// prepare
		counter.setDelay(timeToSleep * SECOND);
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
		counter.setDelay(timeToSleep * MINUTE);
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

	private void close()
	{
		message.dispose();
		counter.stop();
	}
}
