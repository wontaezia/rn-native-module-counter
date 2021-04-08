// Counter.swift
import Foundation

@objc(Counter)
class Counter: RCTEventEmitter {  // RCTEventEmitter 클래스로 변경
  
  private var count = 0
  
  @objc
  func increment() {
    count += 1
    
    // 이 동작이 이루어질 때 해당 이벤트가 작동하게 됩니다.
    // 이벤트에 대한 결과를 body를 통해 전달하게 됩니다.
    sendEvent(withName: "onIncrement", body: ["count": count])
  }

  @objc
  func decrement() {
    count -= 1
    sendEvent(withName: "onDecrement", body: ["count": count])
  }

  @objc
  func getCount(_ callback: RCTResponseSenderBlock) {
    callback([count])
  }
  
  // RCTEventEmitter의 메소드를 새롭게 정의합니다
  // 배열로 리턴됩니다
  override func supportedEvents() -> [String]! {
    return ["onIncrement", "onDecrement"]
  }

  override static func requiresMainQueueSetup() -> Bool {
    return true
  }
}
