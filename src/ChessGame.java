package src;

public class ChessGame {
    private ChessBoard board;
    private boolean whiteTurn = true;

    public ChessGame() {
        this.board = new ChessBoard();
    }

    public boolean makeMove(Position start, Position end) {
        return false;
    }

    public boolean inCheck(PieceColor kingColor) {
        return false;
    }

    public boolean inCheckmate(PieceColor kingColor) {
        return false;
    }

    private boolean isValidPosition(Position position) {
        return false;
    }

    private Position findKingPosition(PieceColor kingColor) {
        return null;
    }

    private boolean wouldBeInCheck(PieceColor kingColor, Position start, Position end) {
        return false;
    }
}