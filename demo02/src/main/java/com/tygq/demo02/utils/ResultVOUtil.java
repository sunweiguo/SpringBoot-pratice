package com.tygq.demo02.utils;

import com.tygq.demo02.vo.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg(msg);
        return resultVO;
    }


    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}