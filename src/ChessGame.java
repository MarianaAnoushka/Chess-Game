package src;
import src.pieces.King;

public class ChessGame {
    private ChessBoard board;
    private boolean whiteTurn = true;

    public ChessGame() {
        this.board = new ChessBoard();
    }

    public boolean makeMove(Position start, Position end) {
        Piece movingPiece = board.getPiece(start.getRow(), start.getColumn());
        if(movingPiece == null || movingPiece.getColor() != (whiteTurn ? PieceColor.WHITE : PieceColor.BLACK)) {
            return false;
        }
        if(movingPiece.isValidMove(end, board.getBoard())) {
            board.movePiece(start, end);
            whiteTurn = !whiteTurn;
            return true;
        }
        return false;
    }

    public boolean inCheck(PieceColor kingColor) {
        Position kingPosition = findKingPosition(kingColor);
        for(int row = 0; row < board.getBoard().length; row++) {
            for(int col = 0; col < board.getBoard()[row].length; col++) {
                Piece piece = board.getPiece(row, col);
                if(piece != null && piece.getColor() != kingColor) {
                    if(piece.isValidMove(kingPosition, board.getBoard())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean inCheckmate(PieceColor kingColor) {

        return false;
    }

    private boolean isValidPosition(Position position) {
        return position.getRow() >= 0 && position.getRow() < board.getBoard().length && position.getColumn() >= 0 && position.getColumn() < board.getBoard()[0].length;
    }

    private Position findKingPosition(PieceColor kingColor) {
        for(int row = 0; row < board.getBoard().length; row++) {
            for(int col = 0; col < board.getBoard()[row].length; col++) {
                Piece piece = board.getPiece(row, col);
                if(piece instanceof King && piece.getColor() == kingColor) {
                    return new Position(row, col);
                }
            }
        }
        throw new RuntimeException("King not found");
    }

    private boolean wouldBeInCheck(PieceColor kingColor, Position start, Position end) {
        return false;
    }
}