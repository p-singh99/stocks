export default function Button({
    caption,
    w,
    h,
}: {
    caption: string,
    w?: string,
    h?: string,
}) {
    let height = h !== undefined ? h : "h-10";
    return (
        <input 
            className={`bg-blue ${w} ${height} rounded-md`}
            type="button"
            value={caption}
        />
    )
}