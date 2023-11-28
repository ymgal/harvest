package com.ymgal.harvest;

import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 * 收成者 —— Harvest
 * 执行入口，输入一个链接
 * 由此工具来负责将这个链接涉及到的信息收纳归一成完整的游戏档案信息（包括机构、角色、人物等等）
 * <p>
 * Create by sorakylin on 2023-10-16
 */
public abstract class Harvest {

    /**
     * 收集的目标游戏档案地址
     */
    private final String gameUrl;

    public Harvest(String gameUrl) {
        if (Objects.isNull(gameUrl)) throw new IllegalArgumentException("The [gameUrl] not be null!");
        this.validateUrl(gameUrl);
        this.gameUrl = gameUrl;
    }

    /**
     * 验证档案地址是否正确的，错误的话直接抛个异常。
     * 这里把valid逻辑单独抽出来，因为子类可能会自定义许多的构造方法，不管怎么都要保证在走这儿一遭
     *
     * @param gameUrl 游戏档案地址，肯定不会为空
     */
    protected abstract void validateUrl(String gameUrl);

    public final CompletableFuture<HarvestResult> get() {
        return get(null, ForkJoinPool.commonPool());
    }

    public final CompletableFuture<HarvestResult> get(InetSocketAddress proxy) {
        return get(proxy, ForkJoinPool.commonPool());
    }

    public final CompletableFuture<HarvestResult> get(Executor executor) {
        return get(null, executor);
    }

    /**
     * 输入一个游戏档案的地址，返回此游戏档案的相关实体及所有关系实体
     *
     * @param proxy    代理, 可能为null
     * @param executor 线程池
     * @return 完整档案
     */
    public final CompletableFuture<HarvestResult> get(InetSocketAddress proxy, Executor executor) {
        if (Objects.isNull(executor)) throw new IllegalArgumentException("The [executor] not be null!");
        return CompletableFuture.supplyAsync(() -> safeExecution(proxy), executor);
    }

    /**
     * 不允许内部异常中断上下文执行
     */
    protected final HarvestResult safeExecution(InetSocketAddress proxy) {
        LocalDateTime start = LocalDateTime.now();

        try {
            HarvestResult res = exec(this.gameUrl, proxy);
            res.setGameUrl(this.gameUrl);
            res.setTaskStartTime(start);
            return res;
        } catch (Exception e) {
            return HarvestResult.error(start, e);
        }
    }

    /**
     * 实际执行的数据获取、过滤、清洗、组合方法，子类自由实现
     *
     * @param gameUrl 游戏地址
     * @param proxy   代理
     * @return
     * @throws Exception
     */
    protected abstract HarvestResult exec(String gameUrl, InetSocketAddress proxy) throws Exception;
}