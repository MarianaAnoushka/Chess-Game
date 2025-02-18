package src;
import src.pieces.King;

public class ChessGame {
    private ChessBoard board;
    private boolean whiteTurn = true;
    private Position selectedPosition;

    public ChessGame() {
        this.board = new ChessBoard();
    }

    public ChessBoard getBoard() {
        return this.board;
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
        if (!inCheck(kingColor)) {
            return false; // Not in check, so cannot be checkmate
        }
        Position kingPosition = findKingPosition(kingColor);
        King king = (King) board.getPiece(kingPosition.getRow(), kingPosition.getColumn());
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (rowOffset == 0 && colOffset == 0) {
                    continue; // Skip the current position of the king
                }
                Position newPosition = new Position(kingPosition.getRow() + rowOffset, kingPosition.getColumn() + colOffset);
                if (isValidPosition(newPosition) && king.isValidMove(newPosition, board.getBoard()) && !wouldBeInCheck(kingColor, kingPosition, newPosition)) {
                    return false;
                }
            }
        }
        return true;
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
        Piece temp = board.getPiece(end.getRow(), end.getColumn());
        board.setPiece(end.getRow(), end.getColumn(), board.getPiece(start.getRow(), start.getColumn()));
        board.setPiece(start.getRow(), start.getColumn(), null);
  
        boolean inCheck = inCheck(kingColor);
        // Undo the move
        board.setPiece(start.getRow(), start.getColumn(), board.getPiece(end.getRow(), end.getColumn()));
        board.setPiece(end.getRow(), end.getColumn(), temp);
  
        return inCheck;
    }

    public PieceColor getCurrentPlayerColor() {
        if(whiteTurn) {
            return PieceColor.WHITE;
        }
        else {
            return PieceColor.BLACK;
        }
    }

    public void resetGame() {
        this.board = new ChessBoard(); // Re-initialize the board
        this.whiteTurn = true; // Reset turn to white
    }

    public boolean isPieceSelected() {
        return selectedPosition != null;
    }

    public boolean handleSquareSelection(int row, int col) {
        if (selectedPosition == null) {
            // Attempt to select a piece
            Piece selectedPiece = board.getPiece(row, col);
            if (selectedPiece != null
                    && selectedPiece.getColor() == (whiteTurn ? PieceColor.WHITE : PieceColor.BLACK)) {
                selectedPosition = new Position(row, col);
                return false; // Indicate a piece was selected but not moved
            }
        } 
        else {
            // Attempt to move the selected piece
            boolean moveMade = makeMove(selectedPosition, new Position(row, col));
            selectedPosition = null; // Reset selection regardless of move success
            return moveMade; // Return true if a move was successfully made
        }
        return false; // Return false if no piece was selected or move was not made
    }
}