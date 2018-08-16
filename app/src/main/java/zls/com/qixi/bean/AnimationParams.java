package zls.com.qixi.bean;

import android.view.View;

import java.util.Collection;
import java.util.List;

import zls.com.qixi.util.CollectionUtil;

/**
 * Created by oop on 2018/8/16.
 */

public class AnimationParams<T extends View> {

    private T target;
    private long duration;
    private float startX;
    private float endX;
    private float startY;
    private float endY;
    private List<Integer> res;
    private int curResIndex = 0;
    private long bgAnimationInterval = 300;

    public AnimationParams(T target, long duration, float startX, float endX, float startY, float endY, List<Integer> res, long bgAnimationInterval) {
        this.target = target;
        this.duration = duration;
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        this.res = res;
        this.bgAnimationInterval = bgAnimationInterval;
    }

    public AnimationParams(){}


    public long getBgAnimationInterval() {
        return bgAnimationInterval;
    }

    public AnimationParams setBgAnimationInterval(long bgAnimationInterval) {
        this.bgAnimationInterval = bgAnimationInterval;
        return this;
    }


    public T getTarget() {
        return target;
    }

    public AnimationParams setTarget(T target) {
        this.target = target;
        return this;
    }

    public long getDuration() {
        return duration;
    }

    public AnimationParams setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public float getStartX() {
        return startX;
    }

    public AnimationParams setStartX(float startX) {
        this.startX = startX;
        return this;
    }

    public float getEndX() {
        return endX;
    }

    public AnimationParams setEndX(float endX) {
        this.endX = endX;
        return this;
    }

    public float getStartY() {
        return startY;
    }

    public AnimationParams setStartY(float startY) {
        this.startY = startY;
        return this;
    }

    public float getEndY() {
        return endY;
    }

    public AnimationParams setEndY(float endY) {
        this.endY = endY;
        return this;
    }

    public List<Integer> getRes() {
        return res;
    }

    public AnimationParams setRes(List<Integer> res) {
        this.res = res;
        return this;
    }

    public int getCurResIndex() {
        return curResIndex;
    }

    public AnimationParams setCurResIndex(int curResIndex) {
        this.curResIndex = curResIndex;
        return this;
    }

    public boolean hasBgAnimation(){
        return !CollectionUtil.empty(res);
    }


}
