package com.iscas.common.web.tools.file.limiter;

/**
 * 下载限流工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/14 21:28
 * @since jdk1.8
 */
public class BandWidthLimiter {
    public  static int maxBandWith = 2 * 1024;
    /** kb */
    public static Long kb = 1024L;
    /** The smallest count chunk length in bytes */
    private static final Long CHUNK_LENGTH = 1024L;
    /** How many bytes will be sent or receive */
    private int bytesWillBeSentOrReceive = 0;
    /** When the last piece was sent or receive */
    private long lastPieceSentOrReceiveTick = System.nanoTime();
    /** Default rate is 1024KB/s */
    private int maxRate = 1024;
    /** Time cost for sending chunkLength bytes in nanoseconds */
    private long timeCostPerChunk = (1000000000L * CHUNK_LENGTH)
            / (this.maxRate * kb);
    /**
     * Initialize a BandwidthLimiter object with a certain rate.
     *
     * @param maxRate the download or upload speed in KBytes
     */
    public BandWidthLimiter(int maxRate) {
        this.setMaxRate(maxRate);
    }
    /**
     * Set the max upload or download rate in kb/s. maxRate must be grater than
     * 0. If maxRate is zero, it means there is no bandwidth limit.
     *
     * @param maxRate If maxRate is zero, it means there is no bandwidth limit.
     */
    public synchronized void setMaxRate(int maxRate)
            throws IllegalArgumentException {
        if (maxRate < 0) {
            throw new IllegalArgumentException("maxRate can not less than 0");
        }
        this.maxRate = maxRate;
        if (maxRate == 0) {
            this.timeCostPerChunk = 0;
        } else {
            this.timeCostPerChunk = (1000000000L * CHUNK_LENGTH)
                        / (this.maxRate * kb);
        }
    }
    /**
     * Next 1 byte should do bandwidth limit.
     */
    public synchronized void limitNextBytes() {
        this.limitNextBytes(1);
    }
    /**
     * Next len bytes should do bandwidth limit
     *
     * @param len len
     */
    public synchronized void limitNextBytes(int len) {
//        int online = FileDownloadUtils.onlineDownloadNumber.get();
//        int max =  online > 0 ? BandWidthLimiter.maxBandWith / online : BandWidthLimiter.maxBandWith;
//        setMaxRate(max);
        this.bytesWillBeSentOrReceive += len;
        /* We have sent chunkLength bytes */
        while (this.bytesWillBeSentOrReceive > CHUNK_LENGTH) {
            long nowTick = System.nanoTime();
            long missedTime = this.timeCostPerChunk
                    - (nowTick - this.lastPieceSentOrReceiveTick);
            if (missedTime > 0) {
                try {
                    //noinspection BusyWait
                    Thread.sleep(missedTime / 1000000,
                            (int) (missedTime % 1000000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.bytesWillBeSentOrReceive -= CHUNK_LENGTH;
            this.lastPieceSentOrReceiveTick = nowTick
                    + (missedTime > 0 ? missedTime : 0);
        }
    }
}
