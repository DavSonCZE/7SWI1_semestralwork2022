import {Suspense, useEffect, useState} from "react";
import {useRouter} from "next/router";
import Image from "next/image";
import useUser from "../../lib/useUser";
import {Dialog} from "@headlessui/react";
import {GetStaticProps, InferGetServerSidePropsType, NextPageContext} from "next";
import useData from "../../lib/useData";
import useSWRImmutable from "swr/immutable";
import {toast, Toaster} from "react-hot-toast";


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

type Review = {
    id: number,
    user: User,
    game: Game,
    score: number,
    reviewComment: string,
}

type User = {
    id: Number,
    username: String,
    email: String,
    password: String
}

const GamePage = () => {
    const router = useRouter();
    const { gameID } = router.query;
    //const [game, setGame] = useState<Game>();
    /*const [reviews, setReviews] = useState<Review[]>();*/
    const [range, setRangeValue] = useState(0);
    const [review, setReview] = useState<Review>();
    const [isOpen, setIsOpen] = useState(false);
    const [editIsOpen, setEditIsOpen] = useState(false);

    //console.log(gameID);

    const {session} = useUser();
    const {data: game}: {data: Game, isLoading: boolean, error: string} = useData(`http://localhost:8090/game/${gameID}`);
    const {data: reviews, isLoading, error: reviewsError}: {data: Review[], isLoading: boolean, error: string} = useData( gameID ? `http://localhost:8090/review/game/${gameID}` : "", {refreshInterval: 3 * 1000, revalidateOnFocus: true, revalidateOnMount: true, revalidateOnReconnect: true});

    console.log({query: gameID, id: game?.id});
    console.log({reviews, isLoading, reviewsError});


    const AddReview = async (e:any) => {
        e.preventDefault();
        const body = {
            user: {id: session?.user?.id},
            game: {id: game?.id},
            score: parseInt(e.target.score.value),
            reviewComment: e.target.reviewComment.value,
        }

        const options = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(body),
        }

        const response = await fetch("http://localhost:8090/review/create", options)
        const result = await response.text();


        toast.success(result);
        setIsOpen(false);
    }

    const EditReview = async (e: any, review: Review | undefined) => {
        e.preventDefault();

        const body = {
            user: {id: session?.user?.id},
            game: {id: game?.id},
            score: parseInt(e.target.score.value),
            reviewComment: e.target.reviewComment.value,
        }

        const response = await fetch(`http://localhost:8090/review/${review?.id}/update`, {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(body),
        })

        const result = await response.text();

        //alert(result);
        toast.success(result);

        setEditIsOpen(false);
    }

    const DeleteReview = async (review: Review | undefined) => {

        const response = await fetch(`http://localhost:8090/review/${review?.id}/delete`, {
            method: "DELETE",
            headers: {"Content-Type": "application/json"},
        })

       const result = await response.text();

        toast(result, {icon: "❌"});

    }

    // @ts-ignore
    return(<>
    <div className="content">
        <div className="content_align">
            <Image src='/gameicon.png' alt='intro_img' width={45} height={45}/>
            <b>{game?.name}</b>
            {isLoading ? <div>Loading data...</div> : (
                <table>
                    <thead>
                    <tr>
                        <th>Username</th>
                        <th>Comment</th>
                        <th>Score</th>
                    </tr>
                    </thead>
                    <tbody>
                    {(reviews != undefined && reviews.length != 0) ? reviews?.map((review: Review) => {
                        return (<tr key={review.id.toString()}>
                            <td>{review.user.username}</td>
                            <td>{review.reviewComment}</td>
                            <td>{review.score.toString()}</td>
                            {(session && (session.isLoggedIn && session.user) && review.user.id == session.user.id) && (
                                <td>
                                    <button onClick={() => {
                                        setReview(review);
                                        setEditIsOpen(true);
                                    }}>Edit
                                    </button>
                                    <button onClick={() => {
                                        DeleteReview(review);
                                    }}>Delete
                                    </button>
                                </td>)}
                        </tr>)
                    }) : <div>Tato hra nemá žádné recenze...</div>}
                    </tbody>
                </table>
            )}
            {(session && (session?.isLoggedIn && session.user)) && !isLoading && (
                    <button disabled={reviews?.find((review: Review) => review.user.id === session?.user?.id) != undefined} onClick={() => {
                        setIsOpen(true)
                    }}>PŘIDAT RECENZI</button>)}
            <Dialog className="dialog" open={isOpen} onClose={() => setIsOpen(false)}>
                <Dialog.Panel className="dialog_content">
                    <Dialog.Title>PŘIDAT RECENZI</Dialog.Title>
                    <form onSubmit={AddReview}>
                        <label htmlFor="comment">Comment:</label><br/>
                        <textarea required id="comment" name="reviewComment"/><br/>
                        <label htmlFor="score">Score: (0-10)</label><br/>
                        <input required value={range} type="range" id="score" name="score" min={0} max={10} step={1} onInput={(e) => {
                            setRangeValue(parseInt(e.currentTarget.value));
                        }}/>
                        <p>{range}</p>
                        <button type="submit"> PŘIDAT</button>
                    </form>
                </Dialog.Panel>
            </Dialog>
            <Dialog className="dialog" open={editIsOpen} onClose={() => setEditIsOpen(false)}>
                <Dialog.Panel className="dialog_content">
                    <Dialog.Title>UPRAVIT RECENZI</Dialog.Title>
                    <form onSubmit={(e) => EditReview(e,review)}>
                        <label htmlFor="comment">Comment:</label><br/>
                        <textarea required id="comment" name="reviewComment" defaultValue={review != undefined ? review.reviewComment : ""}/><br/>
                        <label htmlFor="score">Score: (0-10)</label><br/>
                        <input required defaultValue = {review != undefined ? review.score : range} type="range" id="score" name="score" min={0} max={10} step={1} onInput={(e) => {
                            setRangeValue(parseInt(e.currentTarget.value));
                        }}/>
                        <p>{range}</p>
                        <button type="submit">UPRAVIT</button>
                    </form>
                </Dialog.Panel>
            </Dialog>
        </div>
    </div>
    </>)
}

export default GamePage;