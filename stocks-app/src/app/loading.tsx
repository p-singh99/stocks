import React from 'react'
import './loading.css'
import LoadingAnimation from './components/loadingAnimation/LoadingAnimation'

const Loading = (): React.JSX.Element => {
  return (
        <div className="loading-root">
            <LoadingAnimation />
        </div>
  )
}

export default Loading
