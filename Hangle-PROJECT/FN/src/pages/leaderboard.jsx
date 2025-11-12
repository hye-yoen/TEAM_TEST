import Layout from './Layout.jsx'
import '../css/leaderboard.scss'
import { useEffect, useState } from 'react';


const Leaderboard = () => {

    const [leaderboard, setLeaderboard] = useState(0);
    const [compNameList, setCompNameList] = useState([]);
    const [keyword, setKeyword] = useState("");
    const [isEmpty, setIsEmpty] = useState(false);

    const onSearch = (e) => {
        e.preventDefault();
        const form = new FormData(e.currentTarget);
        const newkeyword = form.get("keyword") || "";
        setKeyword(newkeyword);
    };

    useEffect(() => {
        fetch("http://localhost:8090/api/v1/leaderboard")
            .then((res) => res.json())
            .then((data) => {
                let list = data.leaderboard || [];

                if (keyword.trim() !== "") {
                    list = list.filter(
                        (item) =>
                            item.username.toLowerCase().includes(keyword.toLowerCase()) ||
                            item.compname.toLowerCase().includes(keyword.toLowerCase())
                    );
                }

                setLeaderboard(list);
                const filteredCompList = [...new Set(list.map((item) => item.compname))];
                setCompNameList(filteredCompList);

            })
            .then((data) => { console.log("data : ", data) })
            .catch((err) => console.error(err));
    }, [keyword]);



    // 대회별 그룹핑
    const groupedByComp = compNameList.map((compName) => {
        const entries = leaderboard.filter((entry) => entry.compname === compName);
        return { compName, entries };
    });


    return (
        <main className="main">
            <section className="section-wrap">
                <div>
                    <h1>리더보드 🏆</h1>
                    <p>상위권 참가자의 점수를 확인하세요.</p>
                </div>

                <form className="search" onSubmit={onSearch}>
                    <input name="keyword" placeholder="검색어" />
                    <button className="btn" type="submit">검색</button>
                </form>

                <div>
                    {groupedByComp.map(({ compName, entries }) => (
                        <div key={compName}>
                            <h3>{compName}</h3>
                            <div className="card" style={{ overflowX: "auto" }}>
                                <table className="leaderboard"
                                    style={{ width: "100%", borderCollapse: "collapse" }}>
                                    <thead>
                                        <tr>
                                            <th>순위</th>
                                            <th>닉네임</th>
                                            <th>점수</th>
                                            <th>제출 횟수</th>
                                            <th>최근 제출일</th>
                                        </tr>
                                    </thead>
                                    <tbody className="leaderboardBody">
                                        {entries.map((entry) => (
                                            <tr key={entry.leaderBoardId}>
                                                <td>{entry.comprank}</td>
                                                <td>{entry.username}</td>
                                                <td>{entry.score}</td>
                                                <td>{entry.attempt}</td>
                                                <td>{entry.submittedAt}</td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    ))}

                </div>

            </section>
        </main>
    )
}

export default Leaderboard

