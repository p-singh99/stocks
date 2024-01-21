import React from 'react'

const Button = ({
  caption,
  w,
  h,
  onClickHandler
}: {
  caption: string
  w?: string
  h?: string
  onClickHandler?: () => unknown
}): React.JSX.Element => {
  const height = h ?? 'h-10'
  return (
        <input
            className={`bg-blue cursor-pointer ${w} ${height} rounded-md`}
            type="button"
            value={caption}
            onClick={onClickHandler !== undefined && onClickHandler !== null ? () => { onClickHandler() } : undefined}
        />
  )
}

export default Button
