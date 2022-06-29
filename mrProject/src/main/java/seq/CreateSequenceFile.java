package seq;

import combiner.IPCount2;
import combiner.IPCount2Mapper;
import combiner.IPCount2Reducer;
import lombok.extern.log4j.Log4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/*맵리듀스를 실행하기 위한 Main 함수가 존재하는 자바파일
* 드라이버파일*/
@Log4j
public class CreateSequenceFile extends Configuration implements Tool {


    public static void main(String[] args) throws Exception{
        /*파라미터는 분석할 파일(폴더)와 분석 결과가 저장될 파일(폴더)로 2개를 받음*/
        if(args.length != 2){
            log.info("분석할 폴더(파일) 및 분석결과가 저장될 폴더를 입력해야 합니다.");
            System.exit(-1);
        }
        int exitCode = ToolRunner.run(new CreateSequenceFile(), args);
        System.exit(exitCode);

    }


    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = this.getConf();
        String appName = conf.get("AppName");

        log.info("appName: " + appName);

        /*잡 객체
         * 하둡이 실행되면, 기본적으로 잡 객체를 메모리에 올림
         * */
        Job job = Job.getInstance(conf);

        /*맵리듀스 잡이 시작되는 main합수가 존재하는 파일 설정*/
        job.setJarByClass(CreateSequenceFile.class);

        /*맵리듀스 잡 이름 설정, 리소스 매니저 등 맵리듀스 실행 결과 및 로그 확인할 때 편리*/
        job.setJobName(appName);


        /*분석할 폴더(파일) -- 첫번째 파라미터*/
        FileInputFormat.setInputPaths(job, new Path(strings[0]));

        /*분석 결과가 저장되는 폴더(파일) -- 두 번째 파라미터*/
        FileOutputFormat.setOutputPath(job, new Path(strings[1]));

        /*시퀀스 파일 구조로 변환하기*/
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        /*시퀀스 파일 만들기는 리듀서 작업은 필요하지 않음*/

        /*맵리듀스 실행*/
        boolean success = job.waitForCompletion(true);


        return (success ? 0 : 1);
    }

    @Override
    public void setConf(Configuration configuration) {
        /*App 이름 정의*/
        configuration.set("AppName", "SequenceFile Create Test!!!!!!");
    }

    @Override
    public Configuration getConf() {
        /*맵리듀스 전체에 적용될 변수를 정의할 때 사용*/
        Configuration conf = new Configuration();

        /*변수 정의*/
        this.setConf(conf);

        return conf;
    }
}
