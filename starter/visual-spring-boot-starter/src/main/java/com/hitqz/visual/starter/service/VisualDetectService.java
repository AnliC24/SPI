package com.hitqz.visual.starter.service;

import com.hitqz.sjtc.core.model.ResultObj;
import com.hitqz.visual.starter.entity.VisualRequest;

public interface VisualDetectService {

     ResultObj detectWheelNumber(VisualRequest request);
}
