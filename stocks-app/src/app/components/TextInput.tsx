export default function TextInput({
    text,
    w,
    h,
    htmlId
}: {
    text?: string,
    w?: string,
    h?: string,
    htmlId?: string
}) {
    let height = h !== undefined ? h : "h-10";
    return (
        <input 
            className={`bg-text-input ${height} rounded-md`}
            type="text" 
            id={htmlId} 
            value={text}
        />
    )
}