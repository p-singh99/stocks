'use client';

import { useRouter } from "next/navigation";

export default function Home() {

  const { push } = useRouter();

  if (!isAuthenticated())
    push("/login");

  return (
    <></>
  )
}

const isAuthenticated = (): boolean => {
  return false;
}
