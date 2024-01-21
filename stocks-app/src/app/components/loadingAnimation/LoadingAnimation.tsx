import React from 'react'
import './LoadingAnimation.css'

const LoadingAnimation = (): React.JSX.Element => {
  return (
        <div className="lds-ring">
            <div></div>
            <div></div>
            <div></div>
            <div></div>
        </div>
  )
}

export default LoadingAnimation
