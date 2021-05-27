package com.hadoop_practice;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCountDriver extends Configured implements Tool{

	
	public static void main(String[] args)throws Exception {
		int result=ToolRunner.run(new Configuration(), new WordCountDriver(), args);
		
		if(result==0) {
			System.out.println("job completed");
		}
		else {
			System.out.println("job failed "+result);
		}

	}

	public int run(String[] args) throws Exception {
		Configuration cc=getConf();
		Job job=Job.getInstance(cc,"WordCountJob");
		job.setJarByClass(WordCountDriver.class);
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
		
		return job.isSuccessful()?0:1;
	}

}
