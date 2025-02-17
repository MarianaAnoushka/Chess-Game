package src.pieces;

import src.Piece;
import src.Position;
import src.PieceColor;

public class Queen extends Piece {

    public Queen(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        if(newPosition.equals(position)) {
            return false; //cannot move to same spot
        }
        
        int rowDiff = Math.abs(newPosition.getRow() - this.position.getRow());
        int colDiff = Math.abs(newPosition.getColumn() - this.position.getColumn());
        // Check for straight line movement
        boolean straight = this.position.getRow() == newPosition.getRow() || this.position.getColumn() == newPosition.getColumn();
        // Check for diagonal movement
        boolean gay = rowDiff == colDiff;
        if (!straight && !gay) {
            return false; // The move is neither straight nor diagonal
        }

        int rowDir = Integer.compare(newPosition.getRow(), this.position.getRow());
        int colDir = Integer.compare(newPosition.getColumn(), this.position.getColumn());
  
        // Check for any pieces in the path
        int currentRow = this.position.getRow() + rowDir;
        int currentCol = this.position.getColumn() + colDir;
        while (currentRow != newPosition.getRow() || currentCol != newPosition.getColumn()) {
            if (board[currentRow][currentCol] != null) {
                return false; // Path is blocked
            }
            currentRow += rowDir;
            currentCol += colDir;
        }

        //check if we will be capturing a piece
        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        if(destinationPiece == null) {
            return true; //there is no piece to capture and the move is valid
        }
        else return destinationPiece.getColor() != this.color;
    }
}
