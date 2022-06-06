import {useEffect, useState} from "react";
import {any, number, string} from "prop-types";
import Link from "next/link";
import {GetStaticProps, InferGetServerSidePropsType} from "next";
import useSWR from "swr";
import useData from "../../lib/useData";

type Game = {
    id: Number,
    name: String,
    developer: String,
    releaseYear: Number,
    platform: String,
    price: Number,
    genres: Genre[],
}
type Genre = {
    id: Number,
    name: String,
}

const Games = () =>{
    /*const fetcher = (...args: [ url: string, init: RequestInit ]) => fetch(...args).then(res => res.json());
    const {data: games, error} = useSWR("http://localhost:8090/game/all", fetcher, {refreshInterval: 60000});*/
    const {data: games, error} = useData("http://localhost:8090/game/all");
    // @ts-ignore
    return(<>
        <div className="content_align">
            <h2>Seznam her...</h2>
            {error && alert(error)}
        <table>
            <thead>
                <tr>
                    <td>Title</td>
                    <td>Developer</td>
                    <td>Year of Release</td>
                    <td>Genres</td>
                    <td>Platform</td>
                    <td>Price</td>
                </tr>
            </thead>
            <tbody>
            {games?.map((game: Game) => {
                return(<Link key={game.id.toString()} href={"/games/"+ game.id}>
                            <tr>
                                <td>{game.name}</td>
                                <td>{game.developer}</td>
                                <td>{game.releaseYear.toString()}</td>
                                <td>{game.genres.map(genre => genre.name + ", ")}</td>
                                <td>{game.platform}</td>
                                <td>{game.price.toString()}</td>
                            </tr>
                        </Link>)
            })}
            </tbody>
        </table>
        </div>
    </>);
}

export default Games;