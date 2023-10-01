'use client'

export default function TextInput({
    inputType,
    text,
    w,
    h,
    htmlId,
    setState,
}: {
    inputType?: string,
    text?: string,
    w?: string,
    h?: string,
    htmlId?: string,
    setState?: Function,
}) {
    let height = h !== undefined ? h : "h-10";
    return (
        <input 
            className={`bg-text-input ${height} rounded-md`}
            type={inputType ? inputType : "text"} 
            id={htmlId} 
            value={text}
            onChange={(e) => {setState ? setState(e.target.value) : undefined}}
        />
    )
}