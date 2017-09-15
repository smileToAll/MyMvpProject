package com.example.administrator.myhttptest.mvp.home.bean;

import java.util.List;

/**
 * Created by lijie on 2017/9/15.
 */

public class MeiZhi extends BaseResponse {

    /**
     * error : false
     * results : [{"_id":"59b720df421aa9118887ac18","createdAt":"2017-09-12T07:48:47.73Z","desc":"9-12","publishedAt":"2017-09-14T16:36:51.63Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjgfyxgwgnj20u00gvgmt.jpg","used":true,"who":"daimajia"},{"_id":"59b5cfb5421aa9118887ac0b","createdAt":"2017-09-11T07:50:13.510Z","desc":"9-11","publishedAt":"2017-09-12T08:15:08.26Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjfae1hjslj20u00tyq4x.jpg","used":true,"who":"代码家"},{"_id":"59b0d757421aa901bcb7dc0c","createdAt":"2017-09-07T13:21:27.937Z","desc":"9-7","publishedAt":"2017-09-07T13:25:26.977Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034ly1fjaxhky81vj20u00u0ta1.jpg","used":true,"who":"daimajia"},{"_id":"599f7362421aa901c85e5fc2","createdAt":"2017-08-25T08:46:26.461Z","desc":"8-25","publishedAt":"2017-09-06T12:18:11.687Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fivohbbwlqj20u011idmx.jpg","used":true,"who":"daimajia"},{"_id":"59aca203421aa901c1c0a8d8","createdAt":"2017-09-04T08:44:51.44Z","desc":"09-04","publishedAt":"2017-09-05T11:29:05.240Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fj78mpyvubj20u011idjg.jpg","used":true,"who":"dmj"},{"_id":"59a8cfdc421aa901c1c0a8c7","createdAt":"2017-09-01T11:11:24.81Z","desc":"9-1","publishedAt":"2017-09-01T12:55:52.582Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fj3w0emfcbj20u011iabm.jpg","used":true,"who":"daimajia"},{"_id":"59a755a2421aa901c85e5fea","createdAt":"2017-08-31T08:17:38.117Z","desc":"8-31","publishedAt":"2017-08-31T08:22:07.982Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fj2ld81qvoj20u00xm0y0.jpg","used":true,"who":"daimajia"},{"_id":"59a35f6d421aa901b9dc4643","createdAt":"2017-08-28T08:10:21.141Z","desc":"8-28","publishedAt":"2017-08-29T12:19:18.634Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fiz4ar9pq8j20u010xtbk.jpg","used":true,"who":"代码家"},{"_id":"599e2220421aa901b9dc462d","createdAt":"2017-08-24T08:47:28.949Z","desc":"8-24","publishedAt":"2017-08-24T12:43:10.124Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fiuiw5givwj20u011h79a.jpg","used":true,"who":"daimajia"},{"_id":"599ccace421aa901c85e5fb8","createdAt":"2017-08-23T08:22:38.611Z","desc":"8-23","publishedAt":"2017-08-23T12:12:15.166Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fitcjyruajj20u011h412.jpg","used":true,"who":"daimajia"},{"_id":"599b7cf5421aa901c1c0a867","createdAt":"2017-08-22T08:38:13.732Z","desc":"8-22","publishedAt":"2017-08-22T12:02:15.769Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fis7dvesn6j20u00u0jt4.jpg","used":true,"who":"代码家"},{"_id":"599a299a421aa901b9dc460f","createdAt":"2017-08-21T08:30:18.487Z","desc":"8-21","publishedAt":"2017-08-21T11:38:57.363Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fir1jbpod5j20ip0newh3.jpg","used":true,"who":"daimajia"},{"_id":"599386fe421aa9672cdf0812","createdAt":"2017-08-16T07:42:54.135Z","desc":"8-16","publishedAt":"2017-08-17T11:36:42.967Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fil82i7zsmj20u011hwja.jpg","used":true,"who":"daimajia"},{"_id":"599237dd421aa96729c57246","createdAt":"2017-08-15T07:53:01.962Z","desc":"8-15","publishedAt":"2017-08-15T13:32:36.998Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fik2q1k3noj20u00u07wh.jpg","used":true,"who":"daimajia"},{"_id":"59907386421aa9672cdf07ff","createdAt":"2017-08-13T23:43:02.253Z","desc":"8-13","publishedAt":"2017-08-14T17:04:50.266Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fiiiyfcjdoj20u00u0ju0.jpg","used":true,"who":"dmj"},{"_id":"598bb8f0421aa90ca3bb6c01","createdAt":"2017-08-10T09:37:52.684Z","desc":"8-10","publishedAt":"2017-08-11T14:05:53.749Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fiednrydq8j20u011itfz.jpg","used":true,"who":"带马甲"},{"_id":"598a5478421aa90ca3bb6bfc","createdAt":"2017-08-09T08:16:56.373Z","desc":"8-9","publishedAt":"2017-08-09T13:49:27.260Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fid5poqfznj20u011imzm.jpg","used":true,"who":"daimajia"},{"_id":"598886d9421aa90ca209c570","createdAt":"2017-08-07T23:27:21.296Z","desc":"8-8","publishedAt":"2017-08-08T11:34:20.37Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fibksd2mbmj20u011iacx.jpg","used":true,"who":"daimajia"},{"_id":"59826564421aa90ca3bb6bda","createdAt":"2017-08-03T07:51:00.249Z","desc":"8-3","publishedAt":"2017-08-03T12:08:07.258Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034gy1fi678xgq1ij20pa0vlgo4.jpg","used":true,"who":"代码家"},{"_id":"59810747421aa90ca3bb6bcc","createdAt":"2017-08-02T06:57:11.207Z","desc":"8-2","publishedAt":"2017-08-02T12:21:45.220Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034gy1fi502l3eqjj20u00hz41j.jpg","used":true,"who":"代码家"}]
     */

    private boolean error;
    private List<MeiZhiItemData> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<MeiZhiItemData> getResults() {
        return results;
    }

    public void setResults(List<MeiZhiItemData> results) {
        this.results = results;
    }


}
