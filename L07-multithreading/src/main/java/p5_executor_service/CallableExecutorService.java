package p5_executor_service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableExecutorService {
    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(4, (r) -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
        List<Callable<String>> callableList = new ArrayList<>();
        callableList.add(()->CallableExecutorService.doSomething("Hello"));
        callableList.add(()->CallableExecutorService.doSomething("From"));
        callableList.add(()->CallableExecutorService.doSomething("Java"));
        callableList.add(()->CallableExecutorService.doSomething("Dude"));
        callableList.add(()->CallableExecutorService.doSomething("how"));
        callableList.add(()->CallableExecutorService.doSomething("are"));
        callableList.add(() -> CallableExecutorService.doSomething("you"));
        callableList.add(() -> CallableExecutorService.doSomething("doing"));

        List<Future<String>> listOfResult = new ArrayList<>();

        try {
            listOfResult = es.invokeAll(callableList);
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
            StringBuilder sb = new StringBuilder();
            for(var result : listOfResult) {
                sb.append(result.get());
                sb.append(" ");
            }

            System.out.println(sb.toString());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static String doSomething(String text) {
        try {
            Thread.sleep(4_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return text;
    }
}
