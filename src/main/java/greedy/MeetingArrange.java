package greedy;

import java.util.Arrays;
import java.util.Date;

/**
 * 场次最多的会议安排
 *   会议结束时间早的排前面
 * @author admin
 * @date 2022/9/3 17:48
 */
public class MeetingArrange {


    public static class Meeting{
        Date startTime;
        Date endTime;

        public Meeting(Date startTime, Date endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }


    public static int bestArrange(Meeting[] meetings, Date currentTime){
        int resultNum = 0;
        Arrays.sort(meetings, (m1,m2) -> m1.endTime.compareTo(m2.endTime));
        for (int i = 0; i < meetings.length; i++) {
            if(meetings[i].startTime.compareTo(currentTime) >= 0){
                resultNum++;
                currentTime = meetings[i].endTime;
            }
        }
        return resultNum;
    }





}
