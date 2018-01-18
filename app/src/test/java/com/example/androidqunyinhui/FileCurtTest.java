package com.example.androidqunyinhui;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/18 0018.
 */

public class FileCurtTest {

    @Test
    public void test(){
        List<FileDownloadSize> fileDownloadSizes = calFileDownloadSize(3145728);
        System.out.println(fileDownloadSizes);
        List<FileDownloadSize> fileDownloadSizes1 = calFileDownloadSize(1145728);
        System.out.println(fileDownloadSizes1);
        List<FileDownloadSize> fileDownloadSizes2 = calFileDownloadSize(145728);
        System.out.println(fileDownloadSizes2);
    }

    private List<FileDownloadSize> calFileDownloadSize(long fileSize){
        List<FileDownloadSize> fileDownloadSizes = new ArrayList<>();

        if(fileSize<1048576){
            fileDownloadSizes.add(new FileDownloadSize(0, fileSize));
        }else if(fileSize<3145728){
            long mid = fileSize>>1;
            fileDownloadSizes.add(new FileDownloadSize(0, mid));
            fileDownloadSizes.add(new FileDownloadSize(mid, fileSize));
        }else{
            long divide = fileSize/3;
            fileDownloadSizes.add(new FileDownloadSize(0, divide));
            fileDownloadSizes.add(new FileDownloadSize(divide, divide<<1));
            fileDownloadSizes.add(new FileDownloadSize(divide<<1, fileSize));
        }

        return fileDownloadSizes;
    }

    class FileDownloadSize{
        public long start;
        public long end;

        public FileDownloadSize() {
        }

        public FileDownloadSize(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "start: "+start+"   end: "+end;
        }
    }


}
