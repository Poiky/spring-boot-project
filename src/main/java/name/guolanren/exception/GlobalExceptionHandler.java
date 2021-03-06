package name.guolanren.exception;

import name.guolanren.common.ResultCode;
import name.guolanren.common.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author guolanren
 * @date 2019-01-17
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public ResultEntity requestHandle(RequestException e) {
        LOG.error(e.getMessage());
        return ResultEntity.faild(ResultCode.REQUEST_FAILD, e.getMessage());
    }

    @ExceptionHandler
    public ResultEntity httpMessageNotReadableHandle(HttpMessageNotReadableException e) {
        LOG.error(e.getMessage());
        return ResultEntity.faild(ResultCode.REQUEST_FAILD, e.getMessage());
    }

    @ExceptionHandler
    public ResultEntity internalHandle(InternalException e) {
        LOG.error(e.getMessage());
        return ResultEntity.faild(ResultCode.INTERNAL_FAILD, e.getMessage());
    }

    @ExceptionHandler
    public ResultEntity sessionHandle(SessionException e) {
        LOG.error(e.getMessage());
        return ResultEntity.faild(ResultCode.SESSION_FAILD, e.getMessage());
    }

    @ExceptionHandler
    public ResultEntity unknownHandle(Exception e) {
        LOG.error(e.getMessage());
        return ResultEntity.faild(ResultCode.UNKNOW_FAILD, e.getMessage());
    }

}
