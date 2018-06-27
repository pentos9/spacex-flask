package com.spacex.controller.lock;

import com.spacex.service.lock.DistributedLocker;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("flask")
public class DistributeLockController {

    private Logger logger = LoggerFactory.getLogger(DistributeLockController.class);

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private DistributedLocker distributedLocker;

    @RequestMapping("/lock")
    public String distributeLockWithRedisson() {
        RLock lock = redissonClient.getLock("spacex:flask:");
        try {
            lock.lock();
            logger.info("[Lock] Do something after get lock");
        } finally {
            lock.unlock();
        }
        return "done";
    }

    @RequestMapping("/doLock")
    public String doLock() {
        RLock lock = distributedLocker.lock("spacex:flask");
        try {
            logger.info("[Lock] DistributeLockController#doLock do something after get lock");
        } finally {
            lock.unlock();
        }
        return "done";
    }
}
