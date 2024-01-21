import './loading.css';
import LoadingAnimation from "./components/loadingAnimation/LoadingAnimation";

export default function Loading() {
    return (
        <div className="loading-root">
            <LoadingAnimation />
        </div>
    )
}