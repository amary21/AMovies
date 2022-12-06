TOP_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_PATH 			:= $(TOP_PATH)
LOCAL_MODULE    	:= native-lib
LOCAL_C_INCLUDES 	:= $(LOCAL_PATH)
LOCAL_SRC_FILES 	:= native-lib.cpp
include $(BUILD_SHARED_LIBRARY)