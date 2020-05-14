package com.framework.hanason.core;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.hanason.core.domain.ResultData;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 1,time = 1)
@BenchmarkMode(Mode.AverageTime)
@Fork(value = 2)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Measurement(iterations = 3,time = 3)
public class ResultDataTest {


    public static void main(String[] args) throws RunnerException {
        Options build = new OptionsBuilder().include(ResultDataTest.class.getName())
                .build();
        new Runner(build).run();
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();



    @Benchmark
    @Threads(5)
    public void staticMethod() throws JsonProcessingException {
        OBJECT_MAPPER.writeValueAsString(ResultData.ok());
    }


    @Benchmark
    @Threads(5)
    public void staticField() throws JsonProcessingException {
        OBJECT_MAPPER.writeValueAsString(ResultData.OK);
    }


    @Benchmark
    @Threads(5)
    public void staticMethodFast() {
        JSON.toJSONString(ResultData.ok());
    }


    @Benchmark
    @Threads(5)
    public void staticFieldFast()  {
        JSON.toJSONString(ResultData.OK);
    }




}
