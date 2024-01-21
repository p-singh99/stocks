'use client'

import React from 'react'

const TextInput = ({
  inputType,
  text,
  w,
  h,
  htmlId,
  setState
}: {
  inputType?: string
  text?: string
  w?: string
  h?: string
  htmlId?: string
  setState?: (value: string) => void
}): React.JSX.Element => {
  const height = h ?? 'h-10'
  return (
        <input
            className={`bg-text-input ${height} rounded-md`}
            type={inputType ?? 'text'}
            id={htmlId}
            value={text}
            onChange={setState !== undefined && setState !== null ? (e) => { setState(e.target.value) } : undefined }
        />
  )
}

export default TextInput
