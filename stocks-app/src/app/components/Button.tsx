export default function Button({
    caption,
    w,
    h,
    onClickHandler,
}: {
    caption: string,
    w?: string,
    h?: string,
    onClickHandler?: Function,
}) {
    let height = h !== undefined ? h : "h-10";
    return (
        <input 
            className={`bg-blue cursor-pointer ${w} ${height} rounded-md`}
            type="button"
            value={caption}
            onClick={onClickHandler ? () => onClickHandler() : undefined}
        />
    )
}