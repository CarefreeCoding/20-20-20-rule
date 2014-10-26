import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class ControlWindow extends JFrame
{
	private List<MessageInput> messages;
	private List<JButton>      deletes;

	private TimerControl timer;

	private JPanel  messagePanel;
	private JButton addNewMessage;
	private JButton start;
	private JButton stop;

	private ControlWindow instance;

	public ControlWindow()
	{
		super("Controls");

		instance = this;
		setLayout(new MigLayout("wrap 3, gapy 10"));

		messages = new ArrayList<MessageInput>();
		deletes = new ArrayList<JButton>();

		timer = new TimerControl();

		messagePanel = new JPanel();
		messagePanel.setLayout(new MigLayout("wrap 2, gapy 10"));

		addNewMessage = new JButton("Add New Message");
		start = new JButton("Start");
		stop = new JButton("Stop");

		start.setEnabled(false);
		stop.setEnabled(false);

		addNewMessage.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				addNewMessageInput();
			}
		});

		start.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				start();
			}
		});

		stop.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				stop();
			}
		});

		add(messagePanel, "span 3");
		add(addNewMessage);
		add(start);
		add(stop);

		pack();

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				timer.close();
			}
		});
	}

	private void addNewMessageInput()
	{
		// create input panel and delete button
		final MessageInput input = new MessageInput();
		final JButton delete = new JButton("Delete");

		// add it to the list
		messages.add(input);
		deletes.add(delete);

		// add it to gui
		messagePanel.add(messages.get(messages.size() - 1));
		messagePanel.add(delete);

		// refresh gui
		messagePanel.revalidate();
		instance.revalidate();
		instance.pack();

		// delete action
		delete.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// remove from list
				messages.remove(input);
				deletes.remove(delete);

				// remove from gui
				messagePanel.remove(input);
				messagePanel.remove(delete);

				// if messages are empty disable start otherwise enable
				if (messages.size() == 0)
				{
					start.setEnabled(false);
				}
				else
				{
					start.setEnabled(true);
				}

				// refresh gui
				messagePanel.revalidate();
				instance.revalidate();
				instance.pack();
			}
		});

		// if messages are empty disable start otherwise enable
		if (messages.size() == 0)
		{
			start.setEnabled(false);
		}
		else
		{
			start.setEnabled(true);
		}
	}

	private void start()
	{
		for (MessageInput input : messages)
		{
			input.disable();
		}
		for (JButton delete : deletes)
		{
			delete.setEnabled(false);
		}
		addNewMessage.setEnabled(false);
		start.setEnabled(false);
		stop.setEnabled(true);

		timer.start(messages);
	}

	private void stop()
	{
		for (MessageInput input : messages)
		{
			input.enable();
		}
		for (JButton delete : deletes)
		{
			delete.setEnabled(true);
		}
		addNewMessage.setEnabled(true);
		start.setEnabled(true);
		stop.setEnabled(false);

		timer.stop();
	}
}
