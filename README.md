# tennivaloKezelo / toDo App

## Summary
This is a basic to-do application that works from the console. This project was part of the StudiCore Online curriculum.

<b>Note</b>: classes, variables and methods are in Hungarian. 

## What does it do?

First the program lists the default tasks that was already added to it (or nothing, if it is a fresh start, but I use it as was in the task).
Below that, it shows the menu and asks us what we want to do. 

<a href="https://imgur.com/UsOwI57"><img src="https://i.imgur.com/UsOwI57.png" title="initial state" /></a>

Now we can add a new task by pressing the `a` key and press `Enter`.

<b>Note</b>: If we provide an unsupported letter, or any key, the program tells us that we gave it an invalid input, and quits.

The program asks for the name, deadline, and the severity of the task.

<a href="https://imgur.com/LYwqmmz"><img src="https://i.imgur.com/LYwqmmz.png" title="source: imgur.com" /></a>

As you can see, the new task is added to the list. You might notice, that the ID of the task is 4, instead of 3. Why is that? It is because there is already a completed task added, which is hidden by default.
Let's take a look at it, press `f` and hit `Enter`.

<a href="https://imgur.com/WarX221"><img src="https://i.imgur.com/WarX221.png" title="source: imgur.com" /></a>

There it is! Number 3, marked as finished in the last column. The program lists the unfinished tasks and the menu after every command. Now let's try sorting according to the name of the tasks. Press `e` and as usual, `Enter`.

<a href="https://imgur.com/P7jmgEF"><img src="https://i.imgur.com/P7jmgEF.png" title="source: imgur.com" /></a>

We can see that the unfinished tasks are sorted according to their names. The program can also sort by other criteria, and new options can be easily added.
To exit, press `g` and `Enter`.

This concludes the <b>What does it do?</b> section.

## How does it work?

The program uses a <i>csv</i> file to store the various tasks.

The program consists of various files, here's a short summary about the classes.
* TennivaloKezelo.java : This is the main file. It's responsibility is to keep the tasklist alive until exiting, to ensure that all the data is in the appropiate format, and to show the menu.
* Tennivalo.java : This class represents a task.
* Tennivalok.java : This class is a list of the tasks in the program, and also responsible for using the appropiate Comparator.
* FileIO.java : This class is responsible to read/write the csv file.
* Comparator classes : They all implement the Comparator interface, and responsible for sorting tasks according to their type (e.g.: according to name, or date, etc...).

At startup, the program looks for the <i>csv</i> file that is provided, and reads its contents. If a <i>csv</i> file is not provided, the program starts with an empty list. If the <i>csv</i> exists, but the program cannot validate the data, it will throw an error and exit. If the reading is successful, it will show the unfinished tasks. A `switch` statement controls the options that the user can choose. When sorting, the corresponding code sets the appropiate comparator to the Tennivalok list and invokes the sort method. When a new task is added, it is automatically written to the <i>csv</i> file.
