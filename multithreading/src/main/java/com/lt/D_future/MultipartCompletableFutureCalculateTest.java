package com.lt.D_future;

import com.lt.Z_common.constants.CompletableFutureConstant;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 梁先生
 * @Date 2020/9/2
 * 多个CompletableFuture进行组合运算
 **/
public class MultipartCompletableFutureCalculateTest {
    CompletableFutureConstant constant = new CompletableFutureConstant();

    public CompletableFuture<String> doSomethingOne(String encodedCompanyId) {
        return CompletableFuture.supplyAsync(() -> {
            // 1.1.1休眠1s，模拟任务计算
//            constant.sleep(1000);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 1.1.2 解密，并返回结果
            System.out.println(encodedCompanyId);
            return encodedCompanyId;
        });
    }

    public CompletableFuture<String> doSomethingTwo(String companyId) {
        return CompletableFuture.supplyAsync(() -> {
            // 2.1 休眠3s，模拟计算 try {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 2.2 查询公司信息，转换为str，并返回
            System.out.println(companyId + ":alibaba");
            return companyId + ":alibaba";
        });
    }

    /**
     * 基于thenCompose实现当一个CompletableFuture执行完毕后
     * 执行另外一个CompletableFuture：
     */
    @Test
    public void thenComposeTest() throws ExecutionException, InterruptedException {
        //等doSomethingOne执行完毕后，接着执行doSomethingTwo
        CompletableFuture<String> result = doSomethingOne("213").thenComposeAsync(this::doSomethingTwo);
        System.out.println(result.get());
    }

    /**
     * 基于thenCombine实现当两个并发运行的CompletableFuture任务都完成后，使用两者的结果作为参数再执行一个异步任务，
     */
    @Test
    public void thenCombineTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> combineFuture = doSomethingOne("213")
                .thenCombine(doSomethingTwo("45"), (oneParam, twoParam) -> oneParam + twoParam);
        System.out.println(combineFuture.get());
    }

    /**
     * 基于allOf等待多个并发运行的CompletableFuture任务执行完毕：
     */
    @Test
    public void allOfTest() throws ExecutionException, InterruptedException {
        // 1.创建future列表
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        futureList.add(doSomethingOne("1"));
        futureList.add(doSomethingOne("2"));
        futureList.add(doSomethingOne("3"));
        futureList.add(doSomethingOne("4"));
        futureList.add(doSomethingTwo("5"));
        //2. 转换多个future为一个
        CompletableFuture<Void> allFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));

    }

    /**
     * 基于anyOf等多个并发运行的CompletableFuture任务中有一个执行完毕就返回：
     */
    @Test
    public void anyOfTest() throws ExecutionException, InterruptedException {
        // 1.创建future列表
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        futureList.add(doSomethingTwo("2"));
        futureList.add(doSomethingOne("1"));
        futureList.add(doSomethingTwo("3"));
        futureList.add(doSomethingOne("4"));
        futureList.add(doSomethingTwo("6"));

        // 2.转换多个future为一个
        CompletableFuture<Object> result = CompletableFuture
                .anyOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        // 3.等待某一个future完成
        System.out.println(result.get());
    }

}
