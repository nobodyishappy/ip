package Duke.TaskList;

import Duke.DukeException.DukeException;
import Duke.Storage.Storage;
import Duke.Task.Deadlines.Deadlines;
import Duke.Task.Events.Events;
import Duke.Task.Task;
import Duke.Task.ToDos.ToDos;
import Duke.Ui.Ui;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    public void mark(int num) throws DukeException {
        try {
            Task task = taskList.get(num);
            task.markAsDone();
            Ui.printMark(task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number for the task that is on the list.\n");
        }
    }

    public void unmark(int num) throws DukeException {
        try {
            Task task = taskList.get(num);
            task.markAsUndone();
            Ui.printUnmark(task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number for the task that is on the list.\n");
        }
    }

    public void toDo(String out) {
        Task task = new ToDos(out);
        taskList.add(task);
        Ui.printAdd(task.toString(), taskList.size());
    }

    public void deadline(String description, LocalDate date) {
        Task task = new Deadlines(description, date);
        taskList.add(task);
        Ui.printAdd(task.toString(), taskList.size());
    }

    public void event(String description, String from, String to) {
        Task task = new Events(description, from, to);
        taskList.add(task);
        Ui.printAdd(task.toString(), taskList.size());
    }

    public void delete(int num) throws DukeException {
        try {
            Task task = taskList.get(num);
            taskList.remove(num);
            Ui.printDelete(task.toString(), taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number for the task that is on the list.\n");
        }
    }

    /**
     * Returns list of tasks that contains input.
     *
     * @param input User's input to find similar tasks.
     * @return List of tasks that contains input.
     */
    public ArrayList<Task> findContains(String input) {
        ArrayList<Task> contains = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++){
            if (taskList.get(i).contains(input)) {
                contains.add(taskList.get(i));
            }
        }
        return contains;
    }

    public Task getTask(int num) {
        return taskList.get(num);
    }

    public int getSize() {
        return taskList.size();
    }

    public ArrayList<Task> getList() {
        return taskList;
    }
}
