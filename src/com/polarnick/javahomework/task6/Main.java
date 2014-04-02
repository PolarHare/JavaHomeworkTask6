package com.polarnick.javahomework.task6;

import com.polarnick.javahomework.task6.client.Client;
import com.polarnick.javahomework.task6.server.TaskRunnerImpl;
import com.polarnick.javahomework.task6.tasks.ArraySortTaskFactory;
import com.polarnick.javahomework.task6.tasks.TaskFactory;

/**
 * Date: 02.04.14 at 18:32
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class Main {

    private static final int clientsPerRunnerCount = 2;
    private static final int runnersCount = 2;

    public static void main(String[] args) {
        startRunnersWithClients(new ArraySortTaskFactory(10000));
    }

    private static void startRunnersWithClients(TaskFactory<?, ?> taskFactory) {
        for (int i = 0; i < runnersCount; i++) {
            TaskRunnerImpl runner = startTaskRunner(i);
            for (int j = 0; j < clientsPerRunnerCount; j++) {
                startClient(i * clientsPerRunnerCount + j, taskFactory, i, runner);
            }
        }
    }

    private static void startClient(int id, TaskFactory<?, ?> taskFactory, int runnerId, TaskRunnerImpl runner) {
        Client client = new Client<>(id, runner, taskFactory);
        Thread clientThread = new Thread(client, "Client with id=" + id + " for runner with id=" + runnerId);
        clientThread.start();
    }

    private static TaskRunnerImpl startTaskRunner(int id) {
        TaskRunnerImpl runner = new TaskRunnerImpl(id);
        Thread runnerThread = new Thread(runner, "Runner with id=" + id);
        runnerThread.setDaemon(true);
        runnerThread.start();
        return runner;
    }

}
