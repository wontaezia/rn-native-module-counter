#import <Foundation/Foundation.h>
#import "React/RCTBridgeModule.h"
#import "React/RCTEventEmitter.h" // + RCTEventEmitter

@interface RCT_EXTERN_MODULE(Counter, RCTEventEmitter)

RCT_EXTERN_METHOD(increment)
RCT_EXTERN_METHOD(decrement)
RCT_EXTERN_METHOD(getCount: (RCTResponseSenderBlock)callback)

@end
